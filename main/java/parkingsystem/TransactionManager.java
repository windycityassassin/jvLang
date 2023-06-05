//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package parkingsystem;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class TransactionManager extends ParkingObserver {
    private List<ParkingTransaction> transactions = new ArrayList();

    public TransactionManager() {
        super();
    }

    public void onParkingEventReceived(ParkingEvent event) {
        this.park(event);
    }

    public ParkingTransaction park(ParkingEvent event) {
        Money chargeAmount = event.getParkingLot().getParkingCharge(event);
        ParkingTransaction transaction;
        if (event.getTimeOut() == null) {
            transaction = new ParkingTransaction(event.getTimeIn(), event.getPermit(), event.getParkingLot(), chargeAmount);
        } else {
            transaction = new ParkingTransaction(event.getTimeOut(), event.getPermit(), event.getParkingLot(), chargeAmount);
        }

        System.out.println(String.format("Transaction %s for %s car at parkingLot %s created", transaction.getChargedAmount(), event.getPermit().getCar().getType(), event.getParkingLot().getName()));
        return transaction;
    }

    public ParkingTransaction park(LocalDateTime date, ParkingPermit parkingPermit, ParkingLot parkingLot) {
        Money chargedAmount = parkingLot.getFixedDailyRate();
        ParkingPermit permit = new ParkingPermit();
        ParkingLot parkingLot1 = null;
        Money chargedAmount1 = new Money();
        ParkingTransaction transaction = new ParkingTransaction(date, parkingPermit, parkingLot, chargedAmount, permit, parkingLot1, chargedAmount1);
        this.transactions.add(transaction);
        System.out.println("New transaction created");
        return transaction;
    }

    public Money getParkingCharges(ParkingPermit parkingPermit) {
        List<ParkingTransaction> currentMonthlyTransactions = new ArrayList();
        Month currentMonth = LocalDateTime.now().getMonth();
        int currentYear = LocalDateTime.now().getYear();

        for(int i = 0; i < this.transactions.size(); ++i) {
            if (((ParkingTransaction)this.transactions.get(i)).getDate().getMonth().equals(currentMonth) && ((ParkingTransaction)this.transactions.get(i)).getDate().getYear() == currentYear) {
                currentMonthlyTransactions.add((ParkingTransaction)this.transactions.get(i));
            }
        }

        long sumCharges = 0L;

        for(int i = 0; i < currentMonthlyTransactions.size(); ++i) {
            if (((ParkingTransaction)currentMonthlyTransactions.get(i)).getPermit().equals(parkingPermit)) {
                Money charge = ((ParkingTransaction)currentMonthlyTransactions.get(i)).getChargedAmount();
                sumCharges += charge.getAmount();
            }
        }

        Money total = new Money(Math.toIntExact(sumCharges));
        return total;
    }

    public Money getParkingCharges(Customer customer) {
        List<ParkingTransaction> currentMonthlyTransactions = new ArrayList();
        Month currentMonth = LocalDateTime.now().getMonth();
        int currentYear = LocalDateTime.now().getYear();

        for(int i = 0; i < this.transactions.size(); ++i) {
            if (((ParkingTransaction)this.transactions.get(i)).getDate().getMonth().equals(currentMonth) && ((ParkingTransaction)this.transactions.get(i)).getDate().getYear() == currentYear) {
                currentMonthlyTransactions.add((ParkingTransaction)this.transactions.get(i));
            }
        }

        long sumCharges = 0L;

        for(int i = 0; i < currentMonthlyTransactions.size(); ++i) {
            if (((ParkingTransaction)currentMonthlyTransactions.get(i)).getPermit().getCar().getOwner().equals(customer)) {
                Money charge = ((ParkingTransaction)currentMonthlyTransactions.get(i)).getChargedAmount();
                sumCharges += charge.getAmount();
            }
        }

        Money total = new Money(Math.toIntExact(sumCharges));
        return total;
    }
}
