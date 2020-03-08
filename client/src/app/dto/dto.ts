/* tslint:disable */
/* eslint-disable */
// Generated using typescript-generator version 2.20.583 on 2020-03-08 19:37:13.

export interface ChangePasswordRequest {
    oldPassword: string;
    newPassword: string;
}

export interface PostDto {
    id: number;
    title: string;
    content: string;
    created?: Date;
    modified?: Date;
    authorUsername?: string;
}

export interface UserDto {
    id: number;
    username: string;
    password?: string;
    email?: string;
    roles: string[];
}
