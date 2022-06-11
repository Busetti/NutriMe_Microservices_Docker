import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { User } from '../Model/user';
import { UserService } from '../Service/user-service';

@Component({
  selector: 'app-user-changepassword',
  templateUrl: './user-changepassword.component.html',
  styleUrls: ['./user-changepassword.component.css']
})
export class UserChangepasswordComponent implements OnInit {

  userEmail = "";
  user: User;

  constructor(public dialogRef: MatDialogRef<UserChangepasswordComponent>,
    @Inject(MAT_DIALOG_DATA) public data, private service: UserService) {
    this.userEmail = data.userEmail;
  }

  ngOnInit() {
    this.user = new User();
  }

  changePassword() {
    let user1 = new User();
    user1.emailId = this.userEmail;
    user1.password = this.user.password;
    this.service.changePassword(user1).subscribe(data => {
      this.dialogRef.close();
    },
      error => {
        console.log(error);
      });

  }

}
