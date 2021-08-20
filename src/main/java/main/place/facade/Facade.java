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
    private final ClientRepository clientRepository;
    private final LogRepository logRepository;
    private ValidarDadosObrigatoriosEndereco validarDadosObrigatoriosEndereco;

    public Facade(
                  AddressRepository addressRepository,
                  LogRepository logRepository,
                  ValidarDadosObrigatoriosEndereco validarDadosObrigatoriosEndereco,
                  ClientRepository clientRepository
    ){

        this.addressRepository = addressRepository;
        this.logRepository = logRepository;
        this.validarDadosObrigatoriosEndereco = validarDadosObrigatoriosEndereco;
        this.clientRepository = clientRepository;
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
            return "";
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

    public void initJpa(){
        repositorys = new HashMap<String, JpaRepository>();
        repositorys.put(Address.class.getName(), addressRepository);
        repositorys.put(Address.class.getName(), clientRepository);
        repositorys.put(Log.class.getName(), logRepository);
    }

    public void initStrategy(){
        strategys = new HashMap<String, List<IStrategy>>();
        List<IStrategy> validateEstudante = new ArrayList<IStrategy>();
        List<IStrategy> validateEndereco = new ArrayList<IStrategy>();
        validateEndereco.add(validarDadosObrigatoriosEndereco);
        strategys.put(Address.class.getName(), validateEndereco);
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