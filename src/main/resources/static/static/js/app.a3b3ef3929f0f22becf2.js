webpackJsonp([4],{"7zck":function(e,t){},NHnr:function(e,t,n){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var r=n("7+uW"),o={render:function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("v-app",{attrs:{id:"inspire"}},[n("v-navigation-drawer",{attrs:{app:"",clipped:""},model:{value:e.drawer,callback:function(t){e.drawer=t},expression:"drawer"}},[n("v-list",{attrs:{dense:""}},e._l(e.items,function(t){return n("v-list-item",{key:t.text,attrs:{router:"",to:{name:t.router},exact:"",color:"deep-purple lighten-2"}},[n("v-list-item-action",[n("v-icon",[e._v(e._s(t.icon))])],1),e._v(" "),n("v-list-item-content",[n("v-list-item-title",[e._v("\n            "+e._s(t.text)+"\n          ")])],1)],1)}),1)],1),e._v(" "),n("v-app-bar",{attrs:{app:"",color:"deep-purple",dark:"","clipped-left":""}},[n("v-app-bar-nav-icon",{on:{click:function(t){t.stopPropagation(),e.drawer=!e.drawer}}}),e._v(" "),n("v-toolbar-title",[e._v("Study Board")])],1),e._v(" "),n("v-content",[n("v-container",{attrs:{fluid:"","fill-height":""}},[n("v-layout",{attrs:{"align-center":"","justify-center":""}},[n("router-view")],1)],1)],1),e._v(" "),n("v-footer",{attrs:{color:"deep-purple",dark:"",app:""}},[n("span",[e._v("© 2019")])])],1)},staticRenderFns:[]},a=n("VU/8")({name:"App",data:function(){return{drawer:null,items:[{icon:"home",text:"홈",router:"home"},{icon:"create",text:"글쓰기",router:"regist"},{icon:"create",text:"상세",router:"show"}]}}},o,!1,null,null,null).exports,i=n("/ocq");r.default.use(i.a);var c=new i.a({mode:"history",base:Object({NODE_ENV:"production"}).BASE_URL,routes:[{path:"/",name:"home",component:function(){return n.e(2).then(n.bind(null,"tcnj"))}},{path:"/new",name:"regist",component:function(){return n.e(1).then(n.bind(null,"+GMJ"))}},{path:"/:id",name:"show",component:function(){return n.e(0).then(n.bind(null,"H+wM"))}}]}),l=n("3EgV"),p=n.n(l);n("7zck");r.default.use(p.a);var u=new p.a({icons:{iconfont:"mdi"}});n("u7Vc");r.default.config.productionTip=!1,new r.default({el:"#app",router:c,components:{App:a},vuetify:u,template:"<App/>"})}},["NHnr"]);
//# sourceMappingURL=app.a3b3ef3929f0f22becf2.js.map