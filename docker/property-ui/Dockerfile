FROM node:19-alpine as builder
WORKDIR /app

COPY ../../property-ui/package.json .
COPY ../../property-ui/package-lock.json .
RUN npm install
COPY ../../property-ui .
RUN npm run build

FROM nginx:1.23.3-alpine
WORKDIR /usr/share/nginx/html
RUN rm -rf ./*
COPY --from=builder /app/build .
ENTRYPOINT ["nginx", "-g", "daemon off;"]
