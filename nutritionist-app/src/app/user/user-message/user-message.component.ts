import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-user-message',
  templateUrl: './user-message.component.html',
  styleUrls: ['./user-message.component.css']
})
export class UserMessageComponent implements OnInit {

  errorMessage = "";

  constructor(public dialogRef: MatDialogRef<UserMessageComponent>,
    @Inject(MAT_DIALOG_DATA) public data) {
    this.errorMessage = data.message;
  }

  ngOnInit() {
  }

}
