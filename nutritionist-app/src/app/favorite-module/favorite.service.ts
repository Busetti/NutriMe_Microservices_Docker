import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import { AuthguardService } from '../authentication/authguard.service';
import { Observable} from 'rxjs';
import { environment } from "src/environments/environment";


@Injectable({
  providedIn: 'root'
})
export class FavoriteService {

  public API = `${environment.API_URL}`;

  public SAVE_FAVOURITE = this.API + '/api/v1/favourite/addfavouritefood';

  public DELETE_FAVOURITE = this.API + '/api/v1/favourite/deletefavouritefood';

  public FETCH_FAVOURITE = this.API + '/api/v1/favourite/getfavouritebyusername';

  public FETCH_FOOD = this.API + '/api/v1/favourite/searchfood';

  constructor(private http: HttpClient, private authService: AuthguardService) {
  }

  getFavourites(user: string): Observable<any> {
    console.log("this is from service file getfav", user);
    return this.http.get(`${this.FETCH_FAVOURITE}` + '/' + user);
  }

  deleteFavourites(id: number): Observable<any> {
    console.log("this is from service file delfav", id);
    return this.http.delete(`${this.DELETE_FAVOURITE}` + '/' + id);
  }

  addFavourites(req: any): Observable<any>{
    return this.http.post(`${this.SAVE_FAVOURITE}`, req);
  }

  searchFood(fdcId: any): Observable<any> {
    return this.http.get(`${this.FETCH_FOOD}` + '/' + fdcId);
  }

}
