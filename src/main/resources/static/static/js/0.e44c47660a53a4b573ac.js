webpackJsonp([0],{"H+wM":function(t,e,a){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var n={render:function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("v-container",[a("v-card",{staticClass:"pa-5",attrs:{elevation:"12"}},[a("v-card-text",[a("v-layout",{attrs:{"align-center":"","justify-center":"",row:"","fill-height":""}},[a("v-text-field",{attrs:{label:"No.",type:"text",outlined:"",readonly:""},model:{value:t.document.id,callback:function(e){t.$set(t.document,"id",e)},expression:"document.id"}})],1),t._v(" "),a("v-layout",{attrs:{"align-center":"","justify-center":"",row:"","fill-height":""}},[a("v-text-field",{attrs:{label:"조회수",type:"text",outlined:"",readonly:""},model:{value:t.document.read_count,callback:function(e){t.$set(t.document,"read_count",e)},expression:"document.read_count"}}),t._v(" "),a("v-text-field",{attrs:{label:"생성일자",type:"text",outlined:"",readonly:""},model:{value:t.document.create_date,callback:function(e){t.$set(t.document,"create_date",e)},expression:"document.create_date"}}),t._v(" "),a("v-text-field",{attrs:{label:"수정일자",type:"text",outlined:"",readonly:""},model:{value:t.document.update_date,callback:function(e){t.$set(t.document,"update_date",e)},expression:"document.update_date"}})],1),t._v(" "),a("v-layout",{attrs:{"align-center":"","justify-center":"",row:"","fill-height":""}},[a("v-text-field",{attrs:{label:"제목",type:"text",outlined:"",readonly:""},model:{value:t.document.title,callback:function(e){t.$set(t.document,"title",e)},expression:"document.title"}})],1),t._v(" "),a("v-layout",{attrs:{"align-center":"","justify-center":"",row:"","fill-height":""}},[a("v-textarea",{attrs:{label:"내용",outlined:"",readonly:""},model:{value:t.document.content,callback:function(e){t.$set(t.document,"content",e)},expression:"document.content"}})],1),t._v(" "),a("v-layout",{staticClass:"mb-10",attrs:{"align-center":"","justify-start":"",row:"","fill-height":""}},t._l(t.document.tags,function(e){return a("v-chip",{key:e,staticClass:"mr-1"},[t._v("\n          #"+t._s(e.name)+"\n        ")])}),1),t._v(" "),a("v-layout",{attrs:{"align-center":"","justify-start":"",row:"","fill-height":""}},t._l(t.document.photos,function(e){return a("v-card",{key:e,staticClass:"mr-1",attrs:{flat:""}},[a("v-img",{attrs:{src:""+e.file_path+e.file_name,"max-width":"800","min-width":"200"}},t._l(e.photo_texts,function(e){return a("div",{key:e,style:"position: absolute; top: "+e.position_x+"px; left: "+e.position_y+"px;"},[t._v("\n            "+t._s(e.text)+"\n            ")])}),0)],1)}),1)],1),t._v(" "),a("v-card-actions",[a("v-spacer"),t._v(" "),a("v-btn",{attrs:{color:"red lighten-1",dark:""}},[a("v-icon",[t._v("delete")]),t._v("삭제")],1),t._v(" "),a("v-btn",{attrs:{color:"indigo lighten-1",dark:""}},[a("v-icon",[t._v("edit")]),t._v("수정")],1),t._v(" "),a("v-btn",{attrs:{color:"blue-grey lighten-1",dark:""}},[a("v-icon",[t._v("list")]),t._v("목록")],1)],1)],1)],1)},staticRenderFns:[]},l=a("VU/8")({data:function(){return{document:{id:1,create_date:"2019-08-01 12:36",update_date:"2019-08-07 20:43",read_count:15,title:"제목 작성",content:"내용입니다.",tags:[{tag_id:100,name:"1번태그"},{tag_id:101,name:"2번"},{tag_id:102,name:"귀여워"}],photos:[{photo_id:10,file_path:"http://localhost:8080/",file_name:"favicon.ico"},{photo_id:11,file_path:"http://localhost:8080/",file_name:"favicon.ico"},{photo_id:12,file_path:"../../../images/",file_name:"studyboard-시스템구성도.png"},{photo_id:12,file_path:"/images/",file_name:"image3.jpg"},{photo_id:12,file_path:"/images/",file_name:"image3.jpg"}]}}}},n,!1,null,null,null);e.default=l.exports}});
//# sourceMappingURL=0.e44c47660a53a4b573ac.js.map