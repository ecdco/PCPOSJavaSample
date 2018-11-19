package ir.co.ecd.sample.pcpos;

import ir.co.ecd.pcpos.Serial;

public class Main implements Serial.SerialAfterReceivedListener {

    public static void main(String[] args) {
        Main main = new Main();
        main.start();
    }

    public void start(){
        Serial serial = new Serial();

        serial.setPortName("COM3");
        serial.setSerialAfterReceivedListener(this);

        serial.setSerialNumber("003000009592");
        serial.setTerminalNumber("96090001");
        serial.setMerchantNumber("017379960902001");
        serial.setPaymentType(Serial.PaymentType.Sale);
        serial.setAmount("1000");

        boolean initResult = serial.initCommunication();
        System.out.println("init: " + initResult);
        if (initResult) {
            serial.payment();
        }
    }

    @Override
    public void afterReceived(Serial serial) {
        System.out.println("PAYMENT_TYPE: " + serial.getPaymentType());
        System.out.println("SERIAL_NO   : " + serial.getSerialNumber());
        System.out.println("MERCHANT_NO : " + serial.getMerchantNumber());
        System.out.println("TERMINAL_NO : " + serial.getTerminalNumber());
        System.out.println("STAN        : " + serial.getSTAN());
        System.out.println("RRN         : " + serial.getRRN());
        System.out.println("RES_CODE    : " + serial.getResultCode());
        System.out.println("AMOUNT      : " + serial.getAmount());
        System.out.println("DATETIME    : " + serial.getDateTime());
        System.out.println("PAN         : " + serial.getPAN());
        System.out.println("BALANCE     : " + serial.getBalance());
        System.out.println("DESCRIPTION : " + serial.getDescription());

        boolean r = serial.closeCommunication();
        System.out.println("close: " + r);
    }
}
