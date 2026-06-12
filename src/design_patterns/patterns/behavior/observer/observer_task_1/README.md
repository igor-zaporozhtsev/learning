# Observer Pattern

## Problem

When one object (subject) needs to notify multiple others after an event,
the naive approach is direct coupling:

```java
public class OrderService {
    private final EmailService emailService;
    private final InventoryService inventoryService;
    private final InvoiceService invoiceService;
    private final AuditService auditService;
    private final LoyaltyService loyaltyService;

    public void placeOrder(OrderRequest request) {
        Order order = new Order(request);
        emailService.sendConfirmation(order);
        inventoryService.reserveStock(order);
        invoiceService.generateInvoice(order);
        auditService.logOrderEvent("ORDER_PLACED", order);
        loyaltyService.awardPoints(order.getCustomerId(), order.getTotalAmount());
    }
}
```

**Issues:**
- Adding a new service (e.g. `PushNotificationService`) requires editing `OrderService`
- Constructor grows with every new dependency
- To unit test order creation you must mock all 5 services
- `OrderService` mixes core logic with notification logic

---

## Solution — Observer Pattern

**Define a single observer interface:**

```java
public interface OrderObserver {
    void onOrderPlaced(Order order);
}
```

**Each service implements it:**

```java
public class EmailService implements OrderObserver {
    @Override
    public void onOrderPlaced(Order order) {
        System.out.println("Email sent to " + order.getCustomerId());
    }
}

// same for InventoryService, InvoiceService, AuditService, LoyaltyService
```

**Subject holds a list, not concrete references:**

```java
public class OrderService {
    private final List<OrderObserver> orderObservers;

    public OrderService(List<OrderObserver> orderObservers) {
        this.orderObservers = orderObservers;
    }

    public void placeOrder(OrderRequest request) {
        Order order = new Order(request);
        order.setStatus(OrderStatus.CONFIRMED);
        orderObservers.forEach(observer -> observer.onOrderPlaced(order));
    }
}
```

**Wiring (composition root):**

```java
OrderService orderService = new OrderService(List.of(
    new EmailService(),
    new InventoryService(),
    new InvoiceService(),
    new AuditService(),
    new LoyaltyService()
));
```

---

## Pattern Roles

| Role               | Class                                                                         |
|--------------------|-------------------------------------------------------------------------------|
| Subject            | `OrderService`                                                                |
| Observer interface | `OrderObserver`                                                               |
| Concrete observers | `EmailService`, `InventoryService`, `InvoiceService`, `AuditService`, `LoyaltyService` |
| Event data         | `Order`                                                                       |

---

## Key Benefit

Adding `PushNotificationService` requires:
- ✅ Create class, implement `OrderObserver`
- ✅ Register it in `DemoOrderObserverTask1`
- ❌ Zero changes to `OrderService`

---

## Structure

```
observer_task_1/
├── Order.java
├── OrderRequest.java
├── OrderStatus.java
├── OrderService.java          ← subject (before: tightly coupled, after: uses List<OrderObserver>)
├── EmailService.java          ← observer
├── InventoryService.java      ← observer
├── InvoiceService.java        ← observer
├── AuditService.java          ← observer
├── LoyaltyService.java        ← observer
├── DemoOrderObserverTask1.java ← composition root / main
└── solution/
    └── OrderObserver.java     ← observer interface
```
