import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material';
import { Router } from '@angular/router';
import { Login } from '../Model/login';
import { User } from '../Model/user';
import { UserService } from '../Service/user-service';
import { UserMessageComponent } from '../user-message/user-message.component';

@Component({
  selector: 'app-user-signin',
  templateUrl: './user-signin.component.html',
  styleUrls: ['./user-signin.component.css']
})
export class UserSigninComponent implements OnInit {

  public user: Login;

  constructor(private service: UserService, private route: Router, public dialog: MatDialog) {
    this.user = new Login();
  }

  ngOnInit() {
  }

  signIn() {
    this.service.signin(this.user).subscribe(data => {
      console.log(data);
      sessionStorage.setItem("user", this.user.emailId);
      let tokenStr= 'Bearer '+data.token;
      sessionStorage.setItem("token", tokenStr);
      this.route.navigate(['home']);
      console.log(sessionStorage.getItem("user"));
    },
      error => {
        console.log(error);
        this.showErrorMessage();
      });

  }

  showErrorMessage(): void {
    const dialogRef = this.dialog.open(UserMessageComponent, { 
      data: {message : "Sign in failed. Please try again with correct email and password."}
    });
    dialogRef.afterClosed().subscribe(result => { 
    });
  }

}
