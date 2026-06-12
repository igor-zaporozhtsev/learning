package design_patterns.patterns.structural.proxy;

public class Proxy_DesignPattern {
    public static void main(String[] args) {
        Project image = new ProxyProject("d/path/to/file");
        image.display();
    }
}

interface Project {
    void display();
}

class RealService implements Project {

    String file;

    public RealService(String file) {
        this.file = file;
        load();
    }

    void load(){
        System.out.println("Downloading project form GitHub ...." + file);
    }

    @Override
    //@Transactional
    public void display() {
        System.out.println("Running project from GitHub ...." + file);
    }
}

// lazy downloading ленивая загрузка
class ProxyProject implements Project {

	//TransactionalManager manager;
    String file;
    RealService realService;

    public ProxyProject(String file) {
        this.file = file;
    }

    @Override
    public void display() {
        if (realService == null){
            realService = new RealService(file);
        }

		//add
	   // manager.openTransaction()

        realService.display();

	    // manager.closeTransaction()
    }
}


//приклад

/*


class TransactionalProxy implements Service {

    private final Service target;
    private final TransactionManager txManager;
    private final EntityManagerFactory emf;

    public TransactionalProxy(Service target,
                              TransactionManager txManager,
                              EntityManagerFactory emf) {
        this.target = target;
        this.txManager = txManager;
        this.emf = emf;
    }

    @Override
    public void execute() {

        // 1. begin transaction
        TransactionStatus tx = txManager.getTransaction();

        // 2. create / bind EntityManager (persistence context)
        EntityManager em = emf.createEntityManager();
        EntityManagerHolder.bind(em);

        try {
            // 3. business logic (managed entities live here)
            target.execute();

            // 4. flush changes to DB
            em.flush();

            // 5. commit transaction
            txManager.commit(tx);

        } catch (Exception e) {

            // rollback
            txManager.rollback(tx);

        } finally {

            // 6. close persistence context
            em.close();

            // 7. detach happens implicitly because EM is closed
            EntityManagerHolder.unbind();
        }
    }
}




* */