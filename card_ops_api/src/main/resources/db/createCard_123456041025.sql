CREATE TABLE cards (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    encryptedCardNumber VARCHAR(100) NOT NULL,
    cardholder_name VARCHAR(100) NOT NULL,
    encryptedExpiryDate VARCHAR(100) NOT NULL,
    encryptedCvv VARCHAR(100) NOT NULL,
    createdDate DATETIME2 DEFAULT SYSDATETIME(),
    createdBy VARCHAR(30) DEFAULT "SYSTEM",
    updatedDatet DATETIME2 DEFAULT SYSDATETIME(),
    updatedBy VARCHAR(30) DEFAULT "SYSTEM"
);