# 📱 QR Scanner Login System

## 🌟 Overview

The QR Scanner Login System is a secure and innovative solution designed to streamline the login process. This system integrates QR code technology with email verification powered by the Gmail API and enhances security through the use of JSON Web Tokens (JWT). Upon registration, users receive a unique QR code via email, encrypted with JWT, which they can use for a quick and secure login.

## ✨ Features

- **👤 User Registration**: Users can sign up with their email addresses. Upon registration, the system sends a verification email with a unique QR code, encrypted with JWT, for future logins.
- **🔐 QR Code Login**: For returning users, login is as simple as scanning the QR code provided during registration. The QR scanner detects the JWT encryption, ensuring a secure and hassle-free authentication process.
- **📧 Gmail API Integration**: Utilizes the Gmail API to send emails, ensuring reliable delivery and integration with users' Gmail accounts.
- **🔒 Enhanced Security**: Uses JWT for encrypting QR codes, adding an extra layer of security to protect user data and authentication processes.
- 🗃️ Database Management with MongoDB 🗃️





## 📖 Usage

- **📝 To Register**: Navigate to the registration page and enter your email address. Check your email for the registration confirmation and QR code, which contains encrypted JWT.
- **🔑 To Login**: On the login page, use a QR scanner to scan the QR code sent to your email during registration. The system will decrypt the JWT from the QR code to authenticate the user.

  ## 📱 Demo
  ![Demo](https://github.com/durgesh4040/QRScanner/blob/a490c092c6140e08a324d9eb46ef962709d03850/public/Animation.gif)
  
