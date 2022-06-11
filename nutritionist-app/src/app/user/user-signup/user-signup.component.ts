import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { Login } from '../Model/login';
import { User } from '../Model/user';
import { UserService } from '../Service/user-service';
import { UserMessageComponent } from '../user-message/user-message.component';
@Component({
  selector: 'app-user-signup',
  templateUrl: './user-signup.component.html',
  styleUrls: ['./user-signup.component.css']
})
export class UserSignupComponent implements OnInit {

  public user: User;

  constructor(private service: UserService, public dialog: MatDialog, private route: Router) {
    this.user = new User();
  }

  ngOnInit() {
  }

  saveUser() {
    this.service.saveUser(this.user).subscribe(data => {
      console.log(data);
      let login = new Login();
      login.emailId = this.user.emailId;
      login.password = this.user.password;
      this.service.signin(login).subscribe(data1 => {
        console.log(data1);
        sessionStorage.setItem("user", this.user.emailId);
        let tokenStr= 'Bearer '+data1.token;
        sessionStorage.setItem("token", tokenStr);
        this.route.navigate(['home']);
      },
        error1 => {
          console.log(error1);
        });
    },
      error => {
        console.log(error);
        this.showErrorMessage(error.error);
      });
  }

  showErrorMessage(errorMsg: string): void {
    if(errorMsg == "User already exist"){
      errorMsg = errorMsg + ". Please use another email to sign up or log in to continue."
    }
    else{
      errorMsg = "Something went wrong. Please try again.";
    }
    const dialogRef = this.dialog.open(UserMessageComponent, { 
      data: {message : errorMsg}
    });
    dialogRef.afterClosed().subscribe(result => { 
    });
  }

}
