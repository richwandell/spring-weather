import { Component, OnInit } from '@angular/core';
import {DataService, User} from "../data.service";
import {FormBuilder} from "@angular/forms";

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.scss']
})
export class UsersComponent implements OnInit {

  users: User[] = []

  usersForm = this.formBuilder.group({
    email: '',
    password: ''
  })

  constructor(private dataService: DataService, private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.dataService.sendGetRequest().subscribe((data: User[]) => {
      console.log(data)
      this.users = data
    })
  }

  onSubmit(): void {
    this.dataService.saveNewUser(this.usersForm.value).subscribe((data: any) => {
      console.log(data)
      this.usersForm.reset()
      this.users.push(data)
    })
  }

}
