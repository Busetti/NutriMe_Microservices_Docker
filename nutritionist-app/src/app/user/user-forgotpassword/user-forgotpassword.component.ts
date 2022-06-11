import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { Login } from '../Model/login';
import { User } from '../Model/user';
import { UserService } from '../Service/user-service';
import { UserMessageComponent } from '../user-message/user-message.component';

@Component({
  selector: 'app-user-forgotpassword',
  templateUrl: './user-forgotpassword.component.html',
  styleUrls: ['./user-forgotpassword.component.css']
})
export class UserForgotpasswordComponent implements OnInit {

  public user: Login;
  showPasswordField = false;
  showOtpField = false;
  showGenerateBt = true;
  showValidateBt = false;
  showChangePasswordBt = false;
  disableEmailField = false;
  showLoading = false;
  formMsg = "Please provide an Email address to continue..";

  constructor(private router: Router, private service: UserService, public dialog: MatDialog) {
    this.user = new Login();
  }

  ngOnInit() {
  }

  generateOtp() {
    this.showLoading = true;
    this.service.sendOtp(this.user.emailId).subscribe(data => {
      console.log(data);
      if (data == "Success") {
        this.showLoading = false;
        this.disableEmailField = true;
        this.showOtpField = true;
        this.showGenerateBt = false;
        this.showValidateBt = true;
        this.formMsg = "OTP sent successfully to the Email address and it will be valid for 3 minutes."
      }
      else {
        this.showLoading = false;
        this.showMessage("Please try with correct user email.");
      }
    },
      error => {
        console.log(error);
        this.showLoading = false;
        this.showMessage("Please try with correct user email.");
      });

  }

  validateOtp() {
    this.service.validateOtp(this.user.emailId, this.user.otp).subscribe(data => {
      console.log(data);
      if (data == "Success") {
        this.disableEmailField = true;
        this.showOtpField = false;
        this.showValidateBt = false;
        this.showChangePasswordBt = true;
        this.showPasswordField = true;
        this.formMsg = "OTP verified successfully. Please enter new password."
      }
      else {
        this.showMessage("Please try with correct otp.");
      }
    },
      error => {
        console.log(error);
        this.showMessage("Please try with correct otp.");
      });

  }

  changePassword() {
    if (this.user.password === this.user.confirmpassword) {
      let user1 = new User();
      user1.emailId = this.user.emailId;
      user1.password = this.user.password;
      this.service.changePassword(user1).subscribe(data => {
        console.log(data);
        this.showMessage("Password changed successfully. Please login with new password.");
        this.router.navigateByUrl('/signin');
      },
        error => {
          console.log(error);
          this.showMessage("Something went wrong. Please try again.");
        });
    }
    else {
      this.showMessage("Password and Confirm Password should match. Please try again.");
    }

  }

  routeToSignin(){
    this.router.navigateByUrl('/signin');
  }

  showMessage(msg: string): void {
    const dialogRef = this.dialog.open(UserMessageComponent, {
      data: { message: msg }
    });
    dialogRef.afterClosed().subscribe(result => {
    });
  }


}
