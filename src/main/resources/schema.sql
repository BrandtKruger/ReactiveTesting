CREATE TABLE transaction(
  transactionId SERIAL PRIMARY KEY,
  amount INT,
  amountOther INT,
  merchantId VARCHAR(20) NOT NULL,
  terminalId VARCHAR(20) NOT NULL,
  posId VARCHAR(20) NOT NULL,
  operatorId VARCHAR(20) NOT NULL,
  cardNumber VARCHAR(20) NOT NULL,
  cvv VARCHAR(4) NOT NULL,
  cardExpDate VARCHAR(10) NOT NULL
);