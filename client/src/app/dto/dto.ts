/* tslint:disable */
/* eslint-disable */
// Generated using typescript-generator version 2.20.583 on 2020-03-27 11:22:31.

export interface ChangePasswordRequest {
    oldPassword: string;
    newPassword: string;
}

export interface PostDto {
    id: number;
    title: string;
    preview: string;
    content?: string;
    created?: Date;
    modified?: Date;
    published?: Date;
    authorUsername?: string;
}

export interface PostMetaDto {
    id: number;
    title: string;
    created: Date;
    modified: Date;
    published: Date;
    authorUsername: string;
}

export interface UserDto {
    id: number;
    username: string;
    password?: string;
    email?: string;
    roles: string[];
}
