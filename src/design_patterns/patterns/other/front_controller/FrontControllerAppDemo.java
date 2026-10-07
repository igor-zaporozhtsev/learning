package design_patterns.patterns.other.front_controller;

import java.util.Scanner;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

// =======================
// ENTRY POINT (plays the role of the servlet container, e.g. Tomcat)
// =======================
public class FrontControllerAppDemo {

	public static void main(String[] args) throws InterruptedException {
		// worker pool: one thread per request, like Tomcat
		ExecutorService executor = Executors.newFixedThreadPool(5);

		Router router = new Router();
		router.register("/api/v1/organizations", new OrganizationController());
		router.register("/api/v1/users", new UserController());

		// FRONT CONTROLLER: the single entry point for every request
		DispatcherServlet dispatcher = new DispatcherServlet(router);

		try (Scanner scanner = new Scanner(System.in)) {
			while (scanner.hasNextLine()) {
				String url = scanner.nextLine().trim();
				if (url.isEmpty()) {
					continue;
				}
				executor.submit(() -> dispatcher.process(url));
			}
		} finally {
			executor.shutdown();
			executor.awaitTermination(5, TimeUnit.SECONDS);
		}
	}
}


// =======================
// DISPATCHER SERVLET (the Front Controller, like in Spring MVC)
// =======================
class DispatcherServlet {

	private final Router router;

	public DispatcherServlet(Router router) {
		this.router = router;
	}

	public void process(String url) {
		try {
			Controller controller = router.getController(url);
			controller.handle();
		} catch (Exception e) {
			System.out.println(Thread.currentThread().getName() + ": 500 internal error - " + e.getMessage());
		}
	}
}


// =======================
// ROUTER (HandlerMapping)
// =======================
class Router {

	private final Map<String, Controller> routes = new ConcurrentHashMap<>();
	private final Controller defaultController = new DefaultController();

	public void register(String path, Controller controller) {
		routes.put(path, controller);
	}

	public Controller getController(String path) {
		return routes.getOrDefault(path, defaultController);
	}
}

// =======================
// CONTROLLER CONTRACT
// =======================
interface Controller {
	void handle();
}

// =======================
// CONTROLLERS (singletons shared by all threads -> must be stateless, like Spring beans)
// =======================
class OrganizationController implements Controller {
	public void handle() {
		System.out.println(Thread.currentThread().getName() + ": organizations page");
	}
}

class UserController implements Controller {
	public void handle() {
		System.out.println(Thread.currentThread().getName() + ": users page");
	}
}

class DefaultController implements Controller {
	public void handle() {
		System.out.println(Thread.currentThread().getName() + ": 404 not found");
	}
}