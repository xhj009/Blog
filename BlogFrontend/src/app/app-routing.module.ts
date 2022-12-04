import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './auth/login.component';
import { RegistroComponent } from './auth/registro.component';
import { CategoriaComponent } from './categoria/categoria.component';
import { FormularioCategoriaComponent } from './categoria/formulario-categoria/formulario-categoria.component';
import { ProdGuardService } from './guards/logged.guard';
import { ComentariosComponent } from './post/comentarios/comentarios.component';
import { FormularioComentarioComponent } from './post/comentarios/formulario-comentario/formulario-comentario.component';
import { FormularioPostComponent } from './post/formulario-post/formulario-post.component';
import { PostComponent } from './post/post.component';

const routes: Routes = [
  { path: '', component: LoginComponent },
  { path: 'login', component: LoginComponent },
  { path: 'registro', component: RegistroComponent},
  { path: 'categorias', component: CategoriaComponent,canActivate:[ProdGuardService], data: { expectedRol: ['admin'] }  },
  { path: 'categorias/formulario', component: FormularioCategoriaComponent, canActivate:[ProdGuardService], data: { expectedRol: ['admin'] }  },
  { path: 'categorias/formulario/:id', component: FormularioCategoriaComponent, canActivate:[ProdGuardService], data: { expectedRol: ['admin'] }  },
  { path: 'posts', component: PostComponent, canActivate:[ProdGuardService], data: { expectedRol: ['admin','user'] }},
  { path: 'posts/formulario', component: FormularioPostComponent, canActivate:[ProdGuardService], data: { expectedRol: ['admin','user'] } },
  { path: 'posts/formulario/:id', component: FormularioPostComponent,canActivate:[ProdGuardService], data: { expectedRol: ['admin','user'] } },

  { path: 'posts/comentarios/:id', component: ComentariosComponent,canActivate:[ProdGuardService], data: { expectedRol: ['admin','user'] }},
  { path: 'posts/:id/formulario/comentarios', component: FormularioComentarioComponent,canActivate:[ProdGuardService], data: { expectedRol: ['admin','user'] } },
  { path: 'posts/:id/formulario/comentarios/:comentarioId', component: FormularioComentarioComponent, data: { expectedRol: ['admin','user'] } },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
