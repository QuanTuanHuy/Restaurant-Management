package hust.project.restaurant_management.constants;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(100001, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    UNAUTHENTICATED(100002, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    CREATE_TOKEN_FAILED(100002, "Create token failed", HttpStatus.INTERNAL_SERVER_ERROR),
    UNAUTHORIZED(100003, "You do not have permission", HttpStatus.FORBIDDEN),
    USER_NOT_FOUND(100004, "User not found", HttpStatus.NOT_FOUND),
    USER_EMAIL_EXISTED(100005, "Email existed", HttpStatus.BAD_REQUEST),
    CREATE_USER_FAILED(100006, "Create user failed", HttpStatus.BAD_REQUEST),
    CREATE_ROLE_FAILED(100007, "Create role failed", HttpStatus.BAD_REQUEST),
    ROLE_NOT_FOUND(100008, "Get role failed", HttpStatus.BAD_REQUEST),
    CREATE_MENU_ITEM_FAIL(100009, "Create menu item failed", HttpStatus.BAD_REQUEST),
    MENU_ITEM_NOT_FOUND(100010, "Menu item not found", HttpStatus.NOT_FOUND),
    DELETE_MENU_ITEM_FAILED(100011, "Delete menu item failed", HttpStatus.BAD_REQUEST),
    CREATE_MENU_SECTION_FAILED(100012, "Create menu section failed", HttpStatus.BAD_REQUEST),
    MENU_SECTION_NOT_FOUND(100013, "Menu section not found", HttpStatus.NOT_FOUND),
    DELETE_MENU_SECTION_FAILED(100014, "Delete menu section failed", HttpStatus.BAD_REQUEST),
    CREATE_TABLE_FAILED(100015, "Create table failed", HttpStatus.BAD_REQUEST),
    TABLE_NOT_FOUND(100016, "Table not found", HttpStatus.NOT_FOUND),
    DELETE_TABLE_FAILED(100017, "Delete table failed", HttpStatus.BAD_REQUEST),
    UPDATE_TABLE_FAILED(100018, "Update table failed", HttpStatus.BAD_REQUEST),
    CREATE_ORDER_FAILED(100019, "Create order failed", HttpStatus.BAD_REQUEST),
    ORDER_NOT_FOUND(100020, "Order not found", HttpStatus.NOT_FOUND),
    UPDATE_ORDER_FAILED(100021, "Update order failed", HttpStatus.BAD_REQUEST),
    DELETE_ORDER_FAILED(100021, "Delete order failed", HttpStatus.BAD_REQUEST),
    CREATE_ORDER_ITEM_FAILED(100022, "Create order item failed", HttpStatus.BAD_REQUEST),
    ORDER_ITEM_NOT_FOUND(100023, "Order item not found", HttpStatus.NOT_FOUND),
    DELETE_ORDER_ITEM_FAILED(100024, "Delete order item failed", HttpStatus.BAD_REQUEST),
    CREATE_CUSTOMER_FAILED(100025, "Create customer failed", HttpStatus.BAD_REQUEST),
    CUSTOMER_NOT_FOUND(100026, "Customer not found", HttpStatus.NOT_FOUND),
    DELETE_CUSTOMER_FAILED(100027, "Delete customer failed", HttpStatus.BAD_REQUEST),
    CREATE_ORDER_TABLE_FAILED(100028, "Create order table failed", HttpStatus.BAD_REQUEST),
    DELETE_ORDER_TABLE_FAILED(100029, "Delete order table failed", HttpStatus.BAD_REQUEST),
    UPDATE_ORDER_STATUS_FAILED(100030, "Update order status failed", HttpStatus.BAD_REQUEST),
    MENU_ITEM_QUANTITY_INVALID(100031, "Menu item quantity invalid", HttpStatus.BAD_REQUEST),
    TIME_INVALID(100032, "Time invalid", HttpStatus.BAD_REQUEST),
    ;

    ErrorCode(int code, String message, HttpStatusCode httpStatusCode) {
        this.code = code;
        this.message = message;
        this.httpStatusCode = httpStatusCode;
    }

    private final int code;
    private final String message;
    private final HttpStatusCode httpStatusCode;
}
