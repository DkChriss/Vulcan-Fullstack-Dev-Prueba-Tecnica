export interface Course {
    id: number,
    name: string,
    status: boolean,
    places: number
}

export type CourseStore = Omit<Course, 'id' | 'createdAt' | 'updatedAt'>

export type CourseUpdate = Omit<Course, 'id'| 'name' | 'status' | 'places' >
& Partial<Pick<Course, 'name' | 'status' | 'places' >>
