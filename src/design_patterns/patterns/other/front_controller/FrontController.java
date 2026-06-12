package design_patterns.patterns.other.front_controller;

import java.util.Scanner;

import java.util.*;
import java.util.concurrent.*;

// =======================
// ENTRY POINT (Front Controller)
// =======================
public class FrontController {

	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(5);

		// простий "контейнер"
		Router router = new Router();
		router.register("/home", new HomeController());
		router.register("/user", new UserController());

		DispatcherServlet dispatcher = new DispatcherServlet(router);

		try (Scanner scanner = new Scanner(System.in)) {
			while (scanner.hasNext()) {
				String url = scanner.nextLine();

				executor.submit(() -> dispatcher.process(url));
			}
		}

		executor.shutdown();
	}
}


// =======================
// DISPATCHER (як у Spring)
// =======================
class DispatcherServlet {

	private final Router router;

	public DispatcherServlet(Router router) {
		this.router = router;
	}

	public void process(String url) {
		Controller controller = router.getController(url);
		controller.handle();
	}
}


// =======================
// ROUTER (HandlerMapping)
// =======================
class Router {

	private final Map<String, Controller> routes = new HashMap<>();
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
// CONTROLLERS
// =======================
class HomeController implements Controller {
	public void handle() {
		System.out.println(Thread.currentThread().getName() + ": home page");
	}
}

class UserController implements Controller {
	public void handle() {
		System.out.println(Thread.currentThread().getName() + ": user page");
	}
}

class DefaultController implements Controller {
	public void handle() {
		System.out.println(Thread.currentThread().getName() + ": 404");
	}
}