import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Toast, ToastrService } from 'ngx-toastr';
import { TokenService } from '../auth/token.service';
import { Post } from './post';
import { PostServiceService } from './post-service.service';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {

  posts:Post[] = [];
  totalPages:Array<number> = [];
  page = 0;
  size = 5;
  order = 'id';
  asc = true;
  isFirst = false;
  isLast = false;
  nombreUsuario = '';

  constructor(private postService:PostServiceService,public tokenService:TokenService,private toastr: ToastrService,private activedRouter:ActivatedRoute,private router:Router) { }

  ngOnInit(): void {
    this.getPosts();
    this.nombreUsuario = this.tokenService.getUserName();
  }

  getPosts(){
    this.postService.getAll(this.page,this.size,this.order,this.asc).subscribe(
      data =>{ 
        this.posts = data.content;
        this.isFirst = data.first;
        this.isLast = data.last;
        this.totalPages = new Array(data['totalPages']);
      //console.log(data); 
    }
    );
  }

  rewind():void{
    if(!this.isFirst){
      this.page--;
      this.getPosts();
    }
  }


  forward():void{
    if(!this.isLast){
      this.page++;
      this.getPosts();
    }
  }

  setPage(page:number):void{
    this.page = page;
    this.getPosts();
  }

  delete(post:Post):void{
    this.postService.delete(post.id).subscribe(
      data => {
        this.toastr.success('Post eliminado', 'OK', {
          timeOut: 3000, positionClass: 'toast-top-center'
        });
        this.getPosts();
      }, err => {
        this.toastr.error(err.error.mensaje, 'Fail', {
          timeOut: 3000, positionClass: 'toast-top-center',
        });
      }
    );
  }




}
