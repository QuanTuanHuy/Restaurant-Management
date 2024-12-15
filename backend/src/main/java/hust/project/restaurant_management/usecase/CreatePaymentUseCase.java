package hust.project.restaurant_management.usecase;

import hust.project.restaurant_management.constants.OrderStatusEnum;
import hust.project.restaurant_management.constants.PaymentMethodEnum;
import hust.project.restaurant_management.entity.*;
import hust.project.restaurant_management.entity.dto.request.CreatePaymentRequest;
import hust.project.restaurant_management.entity.dto.request.GetOrderItemKitchenRequest;
import hust.project.restaurant_management.mapper.IPaymentMapper;
import hust.project.restaurant_management.port.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CreatePaymentUseCase {
    private final IOrderPort orderPort;
    private final IPaymentPort paymentPort;
    private final ICustomerPort customerPort;
    private final IPaymentMapper paymentMapper;
    private final IOrderItemKitchenPort orderItemKitchenPort;
    private final IActivityLogPort activityLogPort;
    private final IUserPort userPort;

    @Transactional
    public PaymentEntity createPayment(CreatePaymentRequest request) {
        OrderEntity order = orderPort.getOrderById(request.getOrderId());


        // delete order item kitchens
        List<Long> orderItemKitchenIds = orderItemKitchenPort.getAllOrderItemKitchens(
                GetOrderItemKitchenRequest.builder().orderId(order.getId()).build())
                .stream().map(OrderItemKitchenEntity::getId)
                .toList();

        orderItemKitchenPort.deleteOrderItemKitchensByIds(orderItemKitchenIds);


        // create payment
        PaymentEntity payment = paymentMapper.toEntityFromRequest(request);
        payment.setPaymentMethod(PaymentMethodEnum.valueOf(request.getPaymentMethod()).name());

        PaymentEntity savedPayment = paymentPort.save(payment);


        // update order payment
        order.setPaymentId(savedPayment.getId());
        order.setPaymentMethod(savedPayment.getPaymentMethod());
        order.setOrderStatus(OrderStatusEnum.COMPLETED.name());
        order.setTotalCost(savedPayment.getNeedToPay());

        orderPort.save(order);


        // log activity
        activityLogPort.save(ActivityLogEntity.builder()
                        .userId(order.getUserId())
                        .userName(userPort.getUserById(order.getUserId()).getName())
                        .action("Bán đơn hàng")
                        .amount(savedPayment.getNeedToPay())
                .build());

        CustomerEntity customer = customerPort.getCustomerById(order.getCustomerId());
        customer.setTotalCost(customer.getTotalCost() + savedPayment.getNeedToPay());
        customerPort.save(customer);

        return savedPayment;
    }
}
