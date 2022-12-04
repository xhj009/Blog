import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Observable } from 'rxjs';
import { Categoria } from 'src/app/categoria/categoria';
import { CategoriaServiceService } from 'src/app/categoria/categoria-service.service';
import { CategoriaComponent } from 'src/app/categoria/categoria.component';
import { Post } from '../post';
import { PostSave } from '../post-save';
import { PostServiceService } from '../post-service.service';

@Component({
  selector: 'app-formulario-post',
  templateUrl: './formulario-post.component.html',
  styleUrls: ['./formulario-post.component.css'],
})
export class FormularioPostComponent implements OnInit {
  postSave: PostSave = new PostSave();
  categorias: Array<Categoria> = [];
  page = 0;
  size = 5;
  order = 'id';
  asc = true;

  constructor(
    private postsService: PostServiceService,
    private router: Router,
    private activedRouter: ActivatedRoute,
    private categoriaService: CategoriaServiceService,
    private toastr:ToastrService
  ) {}

  ngOnInit(): void {
    this.cargarDatos();
    this.cargarCategorias();
  }

  cargarDatos(): void {
    const id = this.activedRouter.snapshot.params['id'];
      if (id) {
        this.postsService.get(id).subscribe((e) => {
          this.postSave = e;
        });
      }
    };

  create() {
    this.postsService.create(this.postSave).subscribe(
      (data) => {
        this.toastr.success('Post creado', 'OK', {
          timeOut: 3000, positionClass: 'toast-top-center'
        });
        this.router.navigate(['posts']);
    },err => {
      this.toastr.error("Fallo al crear post revisa todos los campos", 'Fail', {
        timeOut: 3000, positionClass: 'toast-top-center',
      });
    });
  }


 cargarCategorias() {
    this.categoriaService.getAll(this.page, this.size, this.order, this.asc).subscribe(
      (data) => {
        this.categorias = data.content;
      });
  }

  update(): void {
    this.postsService.update(this.postSave.id, this.postSave).subscribe(
      data => {
        this.toastr.success('Post actualizado', 'OK', {
          timeOut: 3000, positionClass: 'toast-top-center'
        });
        this.router.navigate(['/posts'])
        },err => {
          this.toastr.error(err.error.mensaje, 'Fail', {
            timeOut: 3000, positionClass: 'toast-top-center',
          });
        });
  }
}
