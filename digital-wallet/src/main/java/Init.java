import exceptions.UserAlreadyExistsException;
import models.Transaction;
import services.Database;
import services.OfferService;
import services.TransactionService;
import services.WalletService;


public class Init {
    public static void main(String[] args) throws Exception {
        Database db = new Database();
        WalletService walletService = new WalletService(db);
        TransactionService transactionService  = new TransactionService(db);
        OfferService offerService = new OfferService(db);

        walletService.createWallet("Harry", 110);
        walletService.createWallet("Ron", 95.7);
        walletService.createWallet("Hermione", 114);
        walletService.createWallet("Albus", 200);
        walletService.createWallet("Draco", 500);

        System.out.println();
        walletService.overview();

        transactionService.TransferMoney("Albus", "Draco", 30);
        transactionService.TransferMoney("Hermione", "Harry", 2);
        transactionService.TransferMoney("Albus", "Ron", 5);

        System.out.println();
        walletService.overview();

        System.out.println();
        transactionService.Statement("Harry");

        offerService.activateOffer1(10);

        transactionService.Statement("Albus");

        walletService.overview();
        offerService.applyOffer2();
        walletService.overview();

    }
}
