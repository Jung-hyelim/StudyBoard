webpackJsonp([1],{"+GMJ":function(t,e,n){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var o={render:function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",t._l(t.texts,function(e,o){return n("v-flex",{key:"text"+o,attrs:{xs12:""}},[n("v-layout",{attrs:{"align-center":"","justify-start":"",row:""}},[n("v-flex",{attrs:{xs12:"",sm2:""}},[n("v-text-field",{attrs:{label:"x위치",name:"position_x",type:"number",outlined:"","hide-details":""},model:{value:e.position_x,callback:function(n){t.$set(e,"position_x",n)},expression:"list.position_x"}})],1),t._v(" "),n("v-flex",{attrs:{xs12:"",sm2:""}},[n("v-text-field",{attrs:{label:"y위치",name:"position_y",type:"number",outlined:"","hide-details":""},model:{value:e.position_y,callback:function(n){t.$set(e,"position_y",n)},expression:"list.position_y"}})],1),t._v(" "),n("v-text-field",{attrs:{label:"텍스트",name:"text",type:"text",outlined:"","hide-details":""},model:{value:e.text,callback:function(n){t.$set(e,"text",n)},expression:"list.text"}}),t._v(" "),n("v-btn",{attrs:{outlined:"",fab:"",small:"",color:"red lighten-3"},on:{click:function(e){return t.$emit("deleteText",o)}}},[n("v-icon",[t._v("remove")])],1)],1)],1)}),1)},staticRenderFns:[]},l={components:{PhotoTextBox:n("VU/8")({props:["texts"]},o,!1,null,null,null).exports},props:["photos"],data:function(){return{texts:[]}},methods:{addText:function(t){this.texts.push({position_x:"",position_y:"",text:""})},deleteText:function(t){this.texts.splice(t,1)}}},i={render:function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",t._l(t.photos,function(e,o){return n("v-flex",{key:o,attrs:{xs12:"","pb-5":""}},[n("v-layout",{attrs:{"align-center":"","justify-start":"",row:"","fill-height":""}},[n("v-text-field",{attrs:{label:"파일경로",name:"file_path",type:"text",outlined:"","hide-details":""}}),t._v(" "),n("v-text-field",{attrs:{label:"파일이름",name:"file_name",type:"text",outlined:"","hide-details":""}}),t._v(" "),n("v-btn",{attrs:{outlined:"",fab:"",small:"",color:"deep-purple lighten-1"},on:{click:function(e){return t.addText(o)}}},[n("v-icon",[t._v("comment")])],1),t._v(" "),n("v-btn",{attrs:{outlined:"",fab:"",small:"",color:"red lighten-3"},on:{click:function(e){return t.$emit("deletePhoto",o)}}},[n("v-icon",[t._v("clear")])],1)],1),t._v(" "),n("PhotoTextBox",{attrs:{texts:t.texts},on:{deleteText:t.deleteText}})],1)}),1)},staticRenderFns:[]},s={components:{PhotoBox:n("VU/8")(l,i,!1,null,null,null).exports},data:function(){return{mode:"new",document:{},photos:[]}},methods:{addPhoto:function(){this.photos.push({file_path:"",file_name:"",texts:[{position_x:"",position_y:"",text:""}]})},deletePhoto:function(t){this.photos.splice(t,1)}}},a={render:function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("v-container",[n("v-card",{staticClass:"pa-5",attrs:{elevation:"12"}},[n("v-card-text",[n("v-form",["edit"===t.mode?n("v-layout",{attrs:{"align-center":"","justify-center":"",row:"","fill-height":""}},[n("v-text-field",{attrs:{label:"No.",type:"text",outlined:"",readonly:""},model:{value:t.document.id,callback:function(e){t.$set(t.document,"id",e)},expression:"document.id"}})],1):t._e(),t._v(" "),n("v-layout",{attrs:{"align-center":"","justify-center":"",row:"","fill-height":""}},[n("v-text-field",{attrs:{label:"제목",name:"title",type:"text",outlined:""},model:{value:t.document.title,callback:function(e){t.$set(t.document,"title",e)},expression:"document.title"}})],1),t._v(" "),n("v-layout",{attrs:{"align-center":"","justify-center":"",row:"","fill-height":""}},[n("v-textarea",{attrs:{label:"내용",name:"content",outlined:""},model:{value:t.document.content,callback:function(e){t.$set(t.document,"content",e)},expression:"document.content"}})],1),t._v(" "),n("PhotoBox",{attrs:{photos:t.photos},on:{deletePhoto:t.deletePhoto}})],1)],1),t._v(" "),n("v-card-actions",[n("v-btn",{attrs:{outlined:"",fab:"",small:"",color:"deep-purple lighten-1"},on:{click:t.addPhoto}},[n("v-icon",[t._v("add_a_photo")])],1)],1),t._v(" "),n("v-card-actions",[n("v-spacer"),t._v(" "),n("v-btn",{attrs:{color:"indigo lighten-1",dark:""}},[n("v-icon",[t._v("done")]),t._v("저장")],1)],1)],1)],1)},staticRenderFns:[]},r=n("VU/8")(s,a,!1,null,null,null);e.default=r.exports}});
//# sourceMappingURL=1.975eff1d6f7d873e6721.js.map