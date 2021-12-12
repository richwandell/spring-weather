import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";

export interface User {
  id: number;
  email: string;
  password: string;
}

@Injectable({
  providedIn: 'root'
})
export class DataService {

  constructor(private httpClient: HttpClient) {
  }

  public sendGetRequest() {
    return this.httpClient.get<User[]>("/api/users");
  }

  public saveNewUser(user: User) {
    return this.httpClient.post("/api/users", user);
  }
}
