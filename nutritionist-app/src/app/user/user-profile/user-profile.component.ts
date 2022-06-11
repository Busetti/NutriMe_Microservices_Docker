import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { User } from '../Model/user';
import { UserService } from '../Service/user-service';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

  userEmail = "";
  user: User;
  editForm = true;
  disableEditBt = true;
  disableSaveBt = false;

  constructor(public dialogRef: MatDialogRef<UserProfileComponent>,
    @Inject(MAT_DIALOG_DATA) public data, private service: UserService) {
    this.userEmail = data.userEmail;
  }

  ngOnInit() {
    this.user = new User();
    this.getUserInfo();
  }

  getUserInfo() {
      this.service.userInfo(this.userEmail).subscribe(data => {
        this.user = data;
      },
        error => {
          console.log(error);
        });
    }

    enableEdit() {
      this.editForm = false;
      this.disableSaveBt = true;
    }

    updateUser() {
      this.service.updateUserInfo(this.user).subscribe(data => {
        this.user = data;
        this.editForm = true;
        this.disableSaveBt = false;
      },
        error => {
          console.log(error);
        });
    }
  
}
