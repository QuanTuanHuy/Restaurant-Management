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
    CREATE_PRODUCT_FAILED(100033, "Create product failed", HttpStatus.BAD_REQUEST),
    PRODUCT_NOT_FOUND(100034, "Product not found", HttpStatus.NOT_FOUND),
    UPDATE_PRODUCT_FAILED(100035, "Update product failed", HttpStatus.BAD_REQUEST),
    DELETE_PRODUCT_FAILED(100036, "Delete product failed", HttpStatus.BAD_REQUEST),
    CREATE_STOCK_FAILED(100037, "Create stock failed", HttpStatus.BAD_REQUEST),
    STOCK_NOT_FOUND(100038, "Stock not found", HttpStatus.NOT_FOUND),
    DELETE_STOCK_FAILED(100039, "Delete stock failed", HttpStatus.BAD_REQUEST),
    CREATE_SUPPLIER_FAILED(100039, "Create supplier failed", HttpStatus.BAD_REQUEST),
    SUPPLIER_NOT_FOUND(100040, "Supplier not found", HttpStatus.NOT_FOUND),
    UPDATE_SUPPLIER_FAILED(100041, "Update supplier failed", HttpStatus.BAD_REQUEST),
    CREATE_STOCK_HISTORY_FAILED(100042, "Create stock history failed", HttpStatus.BAD_REQUEST),
    STOCK_HISTORY_NOT_FOUND(100043, "Stock history not found", HttpStatus.NOT_FOUND),
    DELETE_STOCK_HISTORY_FAILED(100044, "Delete stock history failed", HttpStatus.BAD_REQUEST),
    CREATE_STOCK_HISTORY_ITEM_FAILED(100044, "Create stock history item failed", HttpStatus.BAD_REQUEST),
    STOCK_HISTORY_ITEM_NOT_FOUND(100045, "Stock history item not found", HttpStatus.NOT_FOUND),
    UPDATE_STOCK_HISTORY_FAILED(100046, "Update stock history failed", HttpStatus.BAD_REQUEST),
    DELETE_STOCK_HISTORY_ITEM_FAILED(100047, "Delete stock history item failed", HttpStatus.BAD_REQUEST),
    SYNC_STOCK_FAILED(100048, "Sync stock failed", HttpStatus.INTERNAL_SERVER_ERROR),
    STOCK_NOT_ENOUGH(100049, "Stock not enough", HttpStatus.BAD_REQUEST),
    CREATE_IMAGE_FAILED(100050, "Create image failed", HttpStatus.BAD_REQUEST),
    IMAGE_NOT_FOUND(100051, "Image not found", HttpStatus.NOT_FOUND),
    DELETE_IMAGE_FAILED(100052, "Delete image failed", HttpStatus.BAD_REQUEST),
    CREATE_SHIFT_FAILED(100053, "Create shift failed", HttpStatus.BAD_REQUEST),
    SHIFT_NOT_FOUND(100054, "Shift not found", HttpStatus.NOT_FOUND),
    DELETE_SHIFT_FAILED(100055, "Delete shift failed", HttpStatus.BAD_REQUEST),
    UPDATE_SHIFT_FAILED(100056, "Update shift failed", HttpStatus.BAD_REQUEST),
    CREATE_WORK_SCHEDULE_FAILED(100057, "Create work schedule failed", HttpStatus.BAD_REQUEST),
    WORK_SCHEDULE_NOT_FOUND(100058, "Work schedule not found", HttpStatus.NOT_FOUND),
    DELETE_WORK_SCHEDULE_FAILED(100059, "Delete work schedule failed", HttpStatus.BAD_REQUEST),
    CREATE_WORK_ATTENDANCE_FAILED(100060, "Create work attendance failed", HttpStatus.BAD_REQUEST),
    WORK_ATTENDANCE_NOT_FOUND(100061, "Work attendance not found", HttpStatus.NOT_FOUND),
    DELETE_WORK_ATTENDANCE_FAILED(100062, "Delete work attendance failed", HttpStatus.BAD_REQUEST),
    UPDATE_WORK_ATTENDANCE_FAILED(100063, "Update work attendance failed", HttpStatus.BAD_REQUEST),
    CREATE_ACTIVITY_LOG_FAILED(100064, "Create activity log failed", HttpStatus.BAD_REQUEST),
    CREATE_ORDER_ITEM_KITCHEN_FAILED(100065, "Create order item kitchen failed", HttpStatus.BAD_REQUEST),
    UPDATE_ORDER_ITEM_KITCHEN_FAILED(100066, "Update order item kitchen failed", HttpStatus.BAD_REQUEST),
    DELETE_ORDER_ITEM_KITCHEN_FAILED(100069, "Delete order item kitchen failed", HttpStatus.BAD_REQUEST),
    CREATE_PAYMENT_FAILED(100067, "Create payment failed", HttpStatus.BAD_REQUEST),
    PAYMENT_NOT_FOUND(100068, "Payment not found", HttpStatus.NOT_FOUND),
    CREATE_SALARY_PERIOD_FAILED(100069, "Create salary period failed", HttpStatus.BAD_REQUEST),
    SALARY_PERIOD_NOT_FOUND(100070, "Salary period not found", HttpStatus.NOT_FOUND),
    DELETE_SALARY_PERIOD_FAILED(100071, "Delete salary period failed", HttpStatus.BAD_REQUEST),
    CREATE_SALARY_DETAIL_FAILED(100072, "Create salary detail failed", HttpStatus.BAD_REQUEST),
    SALARY_DETAIL_NOT_FOUND(100073, "Salary detail not found", HttpStatus.NOT_FOUND),
    DELETE_SALARY_DETAIL_FAILED(100074, "Delete salary detail failed", HttpStatus.BAD_REQUEST),
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
