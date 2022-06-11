import { Component, Input, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { UserService } from 'src/app/user/Service/user-service';
import { UserChangepasswordComponent } from 'src/app/user/user-changepassword/user-changepassword.component';
import { UserProfileComponent } from 'src/app/user/user-profile/user-profile.component';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  username: string;
  @Input() showSignIn = false;

  constructor(private authService: UserService, public dialog: MatDialog) { 
  }

  ngOnInit() {
    this.username = sessionStorage.getItem('user');
  }

  logout(){
    this.authService.logOut();
  }

  displayProfile(){
    this.username = sessionStorage.getItem('user');
    const dialogRef = this.dialog.open(UserProfileComponent, { 
      data: { userEmail : this.username }, width: '60%'
    });
    dialogRef.afterClosed().subscribe(result => { 
    });
  }

  changePassword(){
    this.username = sessionStorage.getItem('user');
    const dialogRef = this.dialog.open(UserChangepasswordComponent, { 
      data: { userEmail : this.username }, width: '30%'
    });
    dialogRef.afterClosed().subscribe(result => { 
    });
  }

}
