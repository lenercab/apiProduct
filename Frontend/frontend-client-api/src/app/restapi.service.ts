import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http'

@Injectable({
  providedIn: 'root'
})
export class RestapiService {

  private url: string = 'http://localhost:8081'

  constructor(private http: HttpClient) { }

  public createInventory(user: string, password: string, body: any): any {
    body = {
      "coinType": "Dolar",
      "nameInventory": "Portatil ACER",
      "numberInventory": 50,
      "unitValue": 13.5
    }
    console.log("RestApiService: createInventory")
    console.log(user + ":" + password)
    const headers = new HttpHeaders({ 
      'Content-Type':  'application/json',
      'Authorization': +  'Basic ' + btoa(user + ":" + password),
      'Access-Control-Allow-Origin':'*',
      'x-terminal':'pc-lener'
     })
    return this.http.post(this.url + '/Inventory', body, { headers })
  }

  public getAllInventory(user: string, password: string): any {
    const headers = new HttpHeaders({ Authorization: +  'Basic ' + btoa(user + ":" + password) })
    this.http.get(this.url + '/Inventory', { headers })
  }
}
