import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { TokenService } from '../auth/token.service';
import { Categoria } from './categoria';
import { CategoriaServiceService } from './categoria-service.service';

@Component({
  selector: 'app-categoria',
  templateUrl: './categoria.component.html',
  styleUrls: ['./categoria.component.css']
})
export class CategoriaComponent implements OnInit {

  categorias:Categoria[] = [];
  totalPages:Array<number> = [];
  page = 0;
  size = 5;
  order = 'id';
  asc = true;
  isFirst = false;
  isLast = false;
  errMsj!: string;

  constructor(private categoriaService:CategoriaServiceService, private toastr: ToastrService
    ) { }

  ngOnInit(): void {
    this.cargarCategorias();
  }

  cargarCategorias(){
    this.categoriaService.getAll(this.page,this.size,this.order,this.asc).subscribe(
      data =>{
        this.categorias = data.content;
        this.isFirst = data.first;
        this.isLast = data.last;
        this.totalPages = new Array(data['totalPages']);
      }
    );
  }

  rewind():void{
    if(!this.isFirst){
      this.page--;
      this.cargarCategorias();
    }
  }


  forward():void{
    if(!this.isLast){
      this.page++;
      this.cargarCategorias();
    }
  }

  setPage(page:number):void{
    this.page = page;
    this.cargarCategorias();
  }

  delete(categoria:Categoria) {
    this.categoriaService.delete(categoria.id).subscribe(
      data => {
        this.toastr.success('Categoria eliminada', 'OK', {
          timeOut: 3000, positionClass: 'toast-top-center'
        });
        this.cargarCategorias();
      },
      err => {
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000, positionClass: 'toast-top-center',
        });
      }
    );
  }
}
