import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { interceptorProvider } from './interceptors/prod-interceptor.service';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToastrModule } from 'ngx-toastr';
import { LoginComponent } from './auth/login.component';
import { RegistroComponent } from './auth/registro.component';
import { MenuComponent } from './menu/menu.component';

import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CategoriaComponent } from './categoria/categoria.component';
import { FormularioCategoriaComponent } from './categoria/formulario-categoria/formulario-categoria.component';
import { FooterComponent } from './footer/footer.component';
import { ComentariosComponent } from './post/comentarios/comentarios.component';
import { PostComponent } from './post/post.component';
import { FormularioPostComponent } from './post/formulario-post/formulario-post.component';
import { FormularioComentarioComponent } from './post/comentarios/formulario-comentario/formulario-comentario.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegistroComponent,
    MenuComponent,
    CategoriaComponent,
    FormularioCategoriaComponent,
    FooterComponent,
    ComentariosComponent,
    FormularioComentarioComponent,
    PostComponent,
    FormularioPostComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot(),
    HttpClientModule,
    FormsModule
  ],
  providers: [interceptorProvider],
  bootstrap: [AppComponent]
})
export class AppModule { }
