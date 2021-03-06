package main.place.facade;

import main.place.entity.*;
import main.place.repository.*;
import main.place.strategy.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class Facade implements IFacade{

    private Map<String, JpaRepository> repositorys;
    private Map<String, List<IStrategy>> strategys;
    private final AddressRepository addressRepository;
    private final UserRepository userRepository;
    private final LogRepository logRepository;
    private final ValidarDadosObrigatoriosEndereco validarDadosObrigatoriosEndereco;
    private final ValidateExistence validateExistence;
    private final ValidateCpf validateCpf;
    private final ValidateClientMandatoryData validateClientMandatoryData;
    private final CreditCardRepository creditCardRepository;
    private final ValidateCreditCardMandatoryData validateCreditCardMandatoryData;
    private final ProductRepository productRepository;
    private final CuponsRepository cuponsRepository;
    private final CategoryRepository categoryRepository;
    private final ProductInvetoryRepository productInvetoryRepository;
    private final PurchaseOrderRepository purchaseOrderRepository;
    private final OrderItemRepository orderItemRepository;
    private final RequestItemsCancelRepository requestItemsCancelRepository;

    public Facade(
                  AddressRepository addressRepository,
                  LogRepository logRepository,
                  ValidarDadosObrigatoriosEndereco validarDadosObrigatoriosEndereco,
                  UserRepository userRepository,
                  ValidateExistence validateExistence,
                  ValidateCpf validateCpf,
                  ValidateClientMandatoryData validateClientMandatoryData,
                  CreditCardRepository creditCardRepository,
                  ValidateCreditCardMandatoryData validateCreditCardMandatoryData,
                  ProductRepository productRepository,
                  CuponsRepository cuponsRepository,
                  CategoryRepository categoryRepository,
                  ProductInvetoryRepository productInvetoryRepository,
                  PurchaseOrderRepository purchaseOrderRepository,
                  OrderItemRepository orderItemRepository,
                  RequestItemsCancelRepository requestItemsCancelRepository
    ){
        this.addressRepository = addressRepository;
        this.logRepository = logRepository;
        this.validarDadosObrigatoriosEndereco = validarDadosObrigatoriosEndereco;
        this.userRepository = userRepository;
        this.validateExistence = validateExistence;
        this.validateCpf = validateCpf;
        this.validateClientMandatoryData = validateClientMandatoryData;
        this.creditCardRepository = creditCardRepository;
        this.validateCreditCardMandatoryData = validateCreditCardMandatoryData;
        this.productRepository = productRepository;
        this.cuponsRepository = cuponsRepository;
        this.categoryRepository = categoryRepository;
        this.productInvetoryRepository = productInvetoryRepository;
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.orderItemRepository = orderItemRepository;
        this.requestItemsCancelRepository = requestItemsCancelRepository;
        initJpa();
        initStrategy();
    }

    @Override
    public EntidadeDominio salvar(EntidadeDominio entidadeDominio) {
        String className = entidadeDominio.getClass().getName();
        String strategyAnswer = executarStrategy(entidadeDominio);
        if(strategyAnswer == null){
            JpaRepository repository = repositorys.get(className);
            EntidadeDominio entidadeRes = (EntidadeDominio) repository.save(entidadeDominio);
            return entidadeRes;
        }
        ReturnMessage returnMessage = new ReturnMessage();
        returnMessage.setReturnMessage(strategyAnswer);
        return returnMessage;
    }

    @Override
    public void deletar(Integer id, EntidadeDominio entidadeDominio) {
        String className = entidadeDominio.getClass().getName();
        JpaRepository repository = repositorys.get(className);
        repository.findById(id)
                .map(entidade ->{
                    repository.delete(entidade);
                    return entidade;
                });
    }

    @Override
    public String alterar(EntidadeDominio entidadeDominio) {
        String className = entidadeDominio.getClass().getName();
        String strategyAnswer = executarStrategy(entidadeDominio);
        if(strategyAnswer == null){
            JpaRepository repository = repositorys.get(className);
            repository.save(entidadeDominio);
            return "sucesso";
        }
        return strategyAnswer;
    }

    @Override
    public Optional<EntidadeDominio> consultar(Integer id, EntidadeDominio entidadeDominio) {
        String className = entidadeDominio.getClass().getName();
        JpaRepository repository = repositorys.get(className);
        return repository.findById(id);
    }

    @Override
    public List<EntidadeDominio> mostrarTodos(EntidadeDominio entidadeDominio) {
        String className = entidadeDominio.getClass().getName();
        JpaRepository repository = repositorys.get(className);
        return repository.findAll();
    }

    /* a????es */
    public void initJpa(){
        repositorys = new HashMap<String, JpaRepository>();
        repositorys.put(Address.class.getName(), addressRepository);
        repositorys.put(User.class.getName(), userRepository);
        repositorys.put(Log.class.getName(), logRepository);
        repositorys.put(CreditCard.class.getName(), creditCardRepository);
        repositorys.put(Product.class.getName(), productRepository);
        repositorys.put(Cupon.class.getName(), cuponsRepository);
        repositorys.put(Category.class.getName(), categoryRepository);
        repositorys.put(ProductInvetory.class.getName(), productInvetoryRepository);
        repositorys.put(PurchaseOrder.class.getName(), purchaseOrderRepository);
        repositorys.put(OrderItem.class.getName(), orderItemRepository);
        repositorys.put(RequestItemsCancel.class.getName(), requestItemsCancelRepository);
    }

    public void initStrategy(){
        strategys = new HashMap<String, List<IStrategy>>();

        /*endere??o*/
        List<IStrategy> validateEndereco = new ArrayList<IStrategy>();
        validateEndereco.add(validarDadosObrigatoriosEndereco);

        /*usuario*/
        List<IStrategy> validateUser = new ArrayList<IStrategy>();
        validateUser.add(validateExistence);
        validateUser.add(validateCpf);
        validateUser.add(validateClientMandatoryData);

        /*creditcard*/
        List<IStrategy> validateCreditCard = new ArrayList<IStrategy>();
        validateCreditCard.add(validateCreditCardMandatoryData);

        /* mapeamento geral */
        strategys.put(Address.class.getName(), validateEndereco);
        strategys.put(User.class.getName(), validateUser);
        strategys.put(CreditCard.class.getName(), validateCreditCard);
        strategys.put(Product.class.getName(), null);
    }

    public String executarStrategy(EntidadeDominio entidade){
        String className = entidade.getClass().getName();
        StringBuilder ErrorMessage = new StringBuilder();
        List<IStrategy> regras = strategys.get(className);

        if (regras != null) {
            for (IStrategy s : regras) {
                String m = s.processar(entidade);
                if (m != null) {
                    ErrorMessage.append(m);
                }
            }
        }
        if (ErrorMessage.length() > 0)
            return ErrorMessage.toString();
        else
            return null;
    }
}
