FROM nginx:latest
COPY nginx.conf /etc/nginx/conf.d/default.conf
COPY cert/study_jhl_com.crt /etc/cert/study_jhl_com.crt
COPY cert/study_jhl_com.key /etc/cert/study_jhl_com.key
CMD ["nginx", "-g", "daemon off;"]