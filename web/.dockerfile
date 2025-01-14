FROM node:20-alpine AS build
WORKDIR /app
COPY . .
RUN npm install -g @angular/cli
RUN npm install
RUN ng build

FROM nginx:alpine
COPY --from=build /app/dist/fuse/ /usr/share/nginx/html
COPY nginx.conf /etc/nginx/conf.d/default.conf
EXPOSE 80
