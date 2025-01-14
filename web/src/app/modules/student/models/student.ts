export interface Student {
  id: number,
  firstName: string,
  lastName: string,
  age: number,
  gender: string
}

export type StudentStore = Omit<Student, 'id' | 'createdAt' | 'updatedAt'>

export type StudentUpdate = Omit<Student, 'id'| 'firstName' | 'lastName' | 'age' | 'gender'>
& Partial<Pick<Student, 'firstName' | 'lastName' | 'age' | 'gender'>>
