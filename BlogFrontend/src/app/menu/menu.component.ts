import { Component, OnInit } from '@angular/core';
import { TokenService } from '../auth/token.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  isLogged = false;
  isAdmin = false;
  nombre = '';

  constructor(private tokenService: TokenService) { }

  ngOnInit() {
   this.isLogged = this.tokenService.isLogged();
   this.isAdmin = this.tokenService.isAdmin();
   this.nombre = this.tokenService.getUserName();

  }

  onLogOut(): void {
    this.tokenService.logOut();
  }

}
