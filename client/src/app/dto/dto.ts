/* tslint:disable */
/* eslint-disable */
// Generated using typescript-generator version 2.20.583 on 2020-03-05 15:43:40.

export interface PostSaveRequest {
    title: string;
    content: string;
}

export interface PostDto {
    id: number;
    title: string;
    content: string;
    created: Date;
    modified: Date;
    authorNick: string;
}

export interface UserDto {
    id: number;
    username: string;
    password?: string;
    email?: string;
    roles: string[];
}
