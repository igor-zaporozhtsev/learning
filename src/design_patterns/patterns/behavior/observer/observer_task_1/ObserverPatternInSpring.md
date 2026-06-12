# Observer Pattern + Spring DI

## Plain Java — manual registration

In plain Java you register observers explicitly at the composition root:

```java
OrderService orderService = new OrderService(List.of(
    new EmailService(),
    new InventoryService(),
    new LoyaltyService()
));
```

Adding a new observer means touching this wiring code.

---

## Spring Boot — automatic registration

Spring automatically collects every bean that implements `OrderObserver`
and injects them into `List<OrderObserver>`.

```java
@Service
@RequiredArgsConstructor
public class OrderService {

    private final List<OrderObserver> orderObservers; // all OrderObserver beans injected automatically

    public void placeOrder(OrderRequest request) {
        Order order = new Order(request);
        order.setStatus(OrderStatus.CONFIRMED);
        orderObservers.forEach(observer -> observer.onOrderPlaced(order));
    }
}
```

Each observer is just a `@Service`:

```java
@Service
public class EmailService implements OrderObserver {
    @Override
    public void onOrderPlaced(Order order) {
        System.out.println("Email sent to " + order.getCustomerId());
    }
}
```

---

## Adding a new observer in Spring

```java
@Service  // ← this single annotation is the registration
public class PushNotificationService implements OrderObserver {
    @Override
    public void onOrderPlaced(Order order) {
        System.out.println("Push notification sent to " + order.getCustomerId());
    }
}
```

**Zero changes to `OrderService`.** Spring picks it up automatically on the next startup.

---

## How it works

When Spring sees `List<OrderObserver>` as a constructor dependency it:
1. Scans the application context for all beans implementing `OrderObserver`
2. Collects them into a list
3. Injects the list into `OrderService`

| Responsibility     | Handled by          |
|--------------------|---------------------|
| Observer contract  | `OrderObserver` interface |
| Dispatch           | Polymorphism        |
| Registration       | Spring DI (`@Service` + `List<T>`) |

## Key insight

> In plain Java you manage the list yourself.
> In Spring Boot the framework manages it — `@Service` on a class that implements the interface is all the registration you need.
