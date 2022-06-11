import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
@Injectable({
  providedIn: 'root'
})
export class SearchModuleService{

  public API = `${environment.API_URL}`;
  public SEARCH_FOOD = this.API + '/api/v1/search/foods';
 
  constructor(private http: HttpClient) {
  }
 
  getFoods(foodname: string): Observable<any> {
    let params = new HttpParams().set("searchQuery",foodname)
    return this.http.get(`${this.SEARCH_FOOD}`, {params: params});
  }
}
