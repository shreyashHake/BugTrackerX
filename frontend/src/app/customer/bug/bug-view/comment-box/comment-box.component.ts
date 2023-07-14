import { AfterViewInit, Component,EventEmitter,Output, ElementRef, ViewChild } from '@angular/core';
import tinymce from 'tinymce';

// import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-comment-box',
  templateUrl: './comment-box.component.html',
  styleUrls: ['./comment-box.component.scss']
})
export class CommentBoxComponent {
  @ViewChild('editorRef') editorRef!: ElementRef;
  editor: any;
  @Output() passComment = new EventEmitter<any>();

  ngAfterViewInit() {
    tinymce.init({
      target: this.editorRef.nativeElement,
      // Additional configuration options
      setup: editor => {
        this.editor = editor;
      }
    });
  }

  accessTextareaValue() {
    if (this.editor) {
        const commentTextValue = this.editor.getContent();
        alert(commentTextValue);
        this.passComment.emit(commentTextValue);
    }
  }
  
}
