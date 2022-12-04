import { Component, OnInit } from '@angular/core';
import { AuthService } from './auth.service';
import { Router } from '@angular/router';
import { LoginUsuario } from './login-usuario';
import { TokenService } from './token.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  nombreUsuario: string = '';
  password: string = '';
  loginUsuario!: LoginUsuario  ;

  errMsj: string = '';

  constructor(
    private tokenService: TokenService,
    private authService: AuthService,
    private router: Router,
    private toastr: ToastrService
  ) { }

  ngOnInit() {
  }

  onLogin(): void {
    this.loginUsuario = new LoginUsuario(this.nombreUsuario, this.password);
    this.authService.login(this.loginUsuario).subscribe(
      data => {
        this.tokenService.setToken(data.token);
        this.router.navigate(['posts'])
      },

      err => {
        //this.errMsj = err.message;
        this.errMsj = 'Comprueba el usuario o la contraseña';
        this.toastr.error(this.errMsj, 'Error', {
          timeOut: 3000,  positionClass: 'toast-top-center',
        });
      }
    );
  }

}
