# todo-list

A simple webapp used to learn some Clojure basic concepts and web libraries like Compojure, Ring and Hiccup.

It can be deployed to heroku or run locally.

It was based on this [tutorial](http://practicalli.github.io/clojure-webapps/).

You can check the application running online, [here](https://thawing-savannah-1718.herokuapp.com/).

## Usage

```shell
lein run 8000
```

## Deployment

```shell
# login and create application
heroku login
heroku create

#deploy new version
git push heroku master
```

## License

Copyright © 2015 luisbebop

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
