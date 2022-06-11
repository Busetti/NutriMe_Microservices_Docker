import { Component } from '@angular/core';
import { NavigationStart, Router } from '@angular/router';
import { UserService } from './user/Service/user-service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'app';

  showSignIn: boolean;

  ngOnInit() {
  }

  constructor(private router: Router, private service: UserService) {
    router.events.forEach((event) => {
      if (event instanceof NavigationStart) {
        if ((event['url'] == '/signin')) {
          this.showSignIn = false;
        } else {
          this.showSignIn = true;
        }
      }
    });
  }
}