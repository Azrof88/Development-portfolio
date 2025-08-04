<?php

use Illuminate\Support\Facades\Route;

Route::get('/', function () {
    return view('welcome');
});

Route::get('/hello/{id?}/comment/{commentvalue?}', function (string $value=null,string $comment=null) {
    if($value)

        {
    return "<h2>Hello:$value.$comment</h2>";
    }
    else
    {
      return "<h1>No Id Found</h1>";
    }
    });

//Route::view('post','/post');
Route::get('/hello/first', function () {
    return view('post');
});
