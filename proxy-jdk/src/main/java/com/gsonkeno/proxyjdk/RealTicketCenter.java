package com.gsonkeno.proxyjdk;

public class RealTicketCenter implements TicketCenter {

    @Override
    public void buyTicket(Integer amount) {
        System.out.println("buyTicket,need ￥" + amount);
    }

    @Override
    public void refundTicket() {
        System.out.println("refundTicket");
    }

    @Override
    public void sellTicket() {
        System.out.println("sellTicket");
    }
}