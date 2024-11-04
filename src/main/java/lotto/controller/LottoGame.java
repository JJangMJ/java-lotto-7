package lotto.controller;

import lotto.error.ErrorMessage;
import lotto.service.LottoGameService;
import lotto.validation.Validation;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoGame {
    private final static int ONE_LOTTO_PRICE = 1000;
    private final InputView inputView;
    private final OutputView outputView;
    private final LottoGameService lottoGameService;

    public LottoGame() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.lottoGameService = new LottoGameService();
    }

    public void start() {
        purchaseLottos();
    }

    private void purchaseLottos() {
        while (true) {
            try {
                int purchasePrice = getValidPurchasePrice();
                int purchaseCount = getPurchaseCount(purchasePrice);
                outputView.printPurchasedLottoCount(purchaseCount);

            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private int getPurchaseCount(int purchasePrice) {
        return purchasePrice / ONE_LOTTO_PRICE;
    }

    private int getValidPurchasePrice() {
        while (true) {
            try {
                String input = inputView.inputPurchasePrice();
                int purchasePrice = parseInputToInt(input);
                Validation.validatePurchasePrice(purchasePrice);
                return purchasePrice;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private int parseInputToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_PURCHASE_PRICE_FORMAT.getMessage());
        }
    }
}
