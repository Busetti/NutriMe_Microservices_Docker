import { BrowserModule } from '@angular/platform-browser';
import { APP_INITIALIZER, NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { UserSignupComponent } from './user/user-signup/user-signup.component';

import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatIconModule} from '@angular/material/icon';  
import {MatGridListModule} from '@angular/material/grid-list';
import {MatButtonModule} from '@angular/material/button';
import {MatDialogModule} from "@angular/material";
import { MatFormFieldModule } from '@angular/material';
import { ReactiveFormsModule  } from '@angular/forms';
import {MatInputModule,MatOptionModule, MatSelectModule} from '@angular/material'
import {MatTabsModule} from '@angular/material/tabs'; 
import {MatCardModule} from '@angular/material/card';
import {MatTableModule} from '@angular/material/table';
import {MatRadioModule} from '@angular/material/radio';
import { UserSigninComponent } from './user/user-signin/user-signin.component';
import { UserService } from './user/Service/user-service';
import { MainSearchComponent } from './search-module/main-search/main-search.component';
import { SearchModuleService } from './search-module/search-modue.service';
import { AuthHtppInterceptorService } from './authentication/auth-htpp-interceptor.service';
import { HeaderComponent } from './common/header/header.component';
import { FooterComponent } from './common/footer/footer.component';
import { HomeComponent } from './common/home/home.component';
import {MatSlideToggleModule} from '@angular/material/slide-toggle';
import {MatTooltipModule} from '@angular/material/tooltip';
import { FoodDetailsComponent } from './search-module/food-details/food-details.component';
import {MatMenuModule} from '@angular/material/menu';
import { UserMessageComponent } from './user/user-message/user-message.component';
import { UserForgotpasswordComponent } from './user/user-forgotpassword/user-forgotpassword.component';
import { AboutusComponent } from './common/aboutus/aboutus.component';
import { MainMealComponent } from './meal-module/main-meal/main-meal.component';
import { MatListModule } from '@angular/material/list';
import { UserProfileComponent } from './user/user-profile/user-profile.component';
import { UserChangepasswordComponent } from './user/user-changepassword/user-changepassword.component';
import { UserBmiComponent } from './user/user-bmi/user-bmi.component';
import { MainFavoritesComponent } from './favorite-module/main-favorites/main-favorites.component';

@NgModule({
  declarations: [
    AppComponent,
    UserSignupComponent,
    UserSigninComponent,
    MainSearchComponent,
    HeaderComponent,
    FooterComponent,
    HomeComponent,
    FoodDetailsComponent,
    UserMessageComponent,
    UserForgotpasswordComponent,
    AboutusComponent,
    MainMealComponent,
    UserProfileComponent,
    UserChangepasswordComponent,
    UserBmiComponent,
    MainFavoritesComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    RouterModule,
    FormsModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatIconModule,
    MatGridListModule,
    MatButtonModule,
    MatDialogModule,
    MatFormFieldModule,
    ReactiveFormsModule,
    MatTabsModule,
    MatInputModule,
    MatOptionModule,
    MatSelectModule,
    MatCardModule,
    MatTableModule,
    MatRadioModule,
    HttpClientModule,
    MatSlideToggleModule,
    MatTooltipModule,
    MatMenuModule,
    MatListModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: AuthHtppInterceptorService, multi: true },
    UserService, SearchModuleService],
  bootstrap: [AppComponent],
  entryComponents:[FoodDetailsComponent, UserMessageComponent, UserProfileComponent, UserChangepasswordComponent]
})
export class AppModule { }
