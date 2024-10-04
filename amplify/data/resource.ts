import { type ClientSchema, a, defineData } from '@aws-amplify/backend';

const schema = a.schema({
  // // enums
  // ClockEventType: a.enum(["START_SHIFT", "BREAK_START", "BACK_TO_WORK", "END_SHIFT"]),
  PayType: a.enum(["HOURLY", "SALARY", "TASK_BASED"]),
  // // many-to-many join tables
  // MemberBreak: a.model({
  //   memberId: a.id().required(),
  //   breakId: a.id().required(),
  //   member: a.belongsTo('Member', 'memberId'),
  //   break: a.belongsTo('Break', 'breakId'),
  // }),
  // MemberLanguage: a.model({
  //   memberId: a.id().required(),
  //   languageId: a.id().required(),
  //   member: a.belongsTo('Member', 'memberId'),
  //   language: a.belongsTo('Language', 'languageId'),
  // }),
  // MemberPosition: a.model({
  //   memberId: a.id().required(),
  //   positionId: a.id().required(),
  //   member: a.belongsTo('Member', 'memberId'),
  //   position: a.belongsTo('Position', 'positionId'),
  // }),
  // MemberShift: a.model({
  //   memberId: a.id().required(),
  //   shiftId: a.id().required(),
  //   member: a.belongsTo('Member', 'memberId'),
  //   shift: a.belongsTo('Shift', 'shiftId'),
  // }),
  // MemberShiftNote: a.model({
  //   memberId: a.id().required(),
  //   shiftNoteId: a.id().required(),
  //   member: a.belongsTo('Member', 'memberId'),
  //   shiftNote: a.belongsTo('ShiftNote', 'shiftNoteId'),
  // }),
  // MemberSkill: a.model({
  //   memberId: a.id().required(),
  //   skillId: a.id().required(),
  //   member: a.belongsTo('Member', 'memberId'),
  //   skill: a.belongsTo('Skill', 'skillId'),
  // }),
  // MemberTeam: a.model({
  //   memberId: a.id().required(),
  //   teamId: a.id().required(),
  //   member: a.belongsTo('Member', 'memberId'),
  //   team: a.belongsTo('Team', 'teamId'),
  // }),
  // // tables
  // // GLOBAL
  // Language: a.model({
  //   name: a.string().required(),
  //   image: a.string().required(),
  //   // many-to-many relationships
  //   members: a.hasMany('MemberLanguage', 'languageId'),
  // }),
  // // BUSINESS SPECIFIC
  // // a break is assigned to each member on a shift (even if a default break is assigned to everyone)
  // // a break is ACTIVE if startTime before current time and endTime is not set
  // // if user forgets to clock in from break then the endTime will just be when shift ends
  // // if user forgets to end shift then automatic clock out after configured time will indicate break end
  // Break: a.model({
  //   startTime: a.time(),
  //   endTime: a.time(), // when the member actually checked back in from the break
  //   duration: a.integer().required(), // configured duration of shift
  //   // one-to-many relationships
  //   shiftId: a.id().required(),
  //   shift: a.belongsTo('Shift', 'shiftId'),
  //   memberId: a.id().required(),
  //   member: a.belongsTo('Member', 'memberId'),
  // }),
  // ClockEvent: a.model({
  //   type: a.ref('ClockEventType').required(),
  //   time: a.time().required(),
  //   breaksTaken: a.boolean().default(true),
  //   // one-to-many relationships
  //   memberId: a.id().required(),
  //   member: a.belongsTo('Member', 'memberId'),
  //   shiftId: a.id().required(),
  //   shift: a.belongsTo('Shift', 'shiftId'),
  // }),
  Member: a.model({
    userId: a.id().required(),
    name: a.string().required(),
    employeeId: a.string(),
    phone: a.phone(),
    email: a.email(),
    onCall: a.boolean().default(false),
    payType: a.ref("PayType"),
    pay: a.float(),
    startDate: a.date(),
    birthDate: a.date(),
    active: a.boolean().default(true),
    // // one-to-many relationships
    // clockEvents: a.hasMany('ClockEvent', 'memberId'),
    // // many-to-many relationships
    // breaks: a.hasMany('MemberBreak', 'memberId'),
    // languages: a.hasMany('MemberLanguage', 'memberId'),
    // positions: a.hasMany('MemberPosition', 'memberId'),
    // skills: a.hasMany('MemberSkill', 'memberId'),
    // teams: a.hasMany('MemberTeam', 'memberId'),
    // shifts: a.hasMany('MemberShift', 'memberId'),
    // shiftNotes: a.hasMany('MemberShiftNote', 'memberId'),
  })
    .identifier(["userId"])
  // Position: a.model({
  //   name: a.string().required(),
  //   // one-to-many relationships
  //   teamId: a.id().required(),
  //   team: a.belongsTo('Team', 'teamId'),
  //   // many-to-many relationships
  //   members: a.hasMany('MemberPosition', 'positionId'),
  // }),
  // Shift: a.model({
  //   isOpen: a.boolean().default(false),
  //   startTime: a.time().required(),
  //   endTime: a.time().required(),
  //   numberOfStaff: a.integer().required(),
  //   // one-to-many relationships
  //   breaks: a.hasMany('Break', 'shiftId'),
  //   clockEvents: a.hasMany('ClockEvent', 'shiftId'),
  //   shiftNotes: a.hasMany('ShiftNote', 'shiftId'),
  //   positionId: a.id().required(),
  //   position: a.belongsTo('Position', 'positionId'),
  //   teamId: a.id().required(),
  //   team: a.belongsTo('Team', 'teamId'),
  //   // many-to-many relationships
  //   assignedMembers: a.hasMany('MemberShift', 'shiftId'),
  // }),
  // ShiftNote: a.model({
  //   content: a.string().required(),
  //   visibleToAll: a.boolean().default(true),
  //   // one-to-many relationships
  //   shiftId: a.id().required(),
  //   shift: a.belongsTo('Shift', 'shiftId'),
  //   // many-to-many relationships
  //   visibleTo: a.hasMany('MemberShiftNote', 'shiftNoteId'),
  // }),
  // Skill: a.model({
  //   name: a.string().required(),
  //   // one-to-many relationships
  //   teamId: a.id().required(),
  //   team: a.belongsTo('Team', 'teamId'),
  //   // many-to-many relationships
  //   members: a.hasMany('MemberSkill', 'skillId'),
  // }),
  // Team: a.model({
  //   name: a.string().required(),
  //   icon: a.string(),
  //   // one-to-many relationships
  //   positions: a.hasMany('Position', 'teamId'),
  //   shifts: a.hasMany('Shift', 'teamId'),
  //   skills: a.hasMany('Skill', 'teamId'),
  //   // many-to-many relationships
  //   members: a.hasMany('MemberTeam', 'teamId'),
  // })
}).authorization((allow) => [allow.owner()]);

export type Schema = ClientSchema<typeof schema>;

export const data = defineData({
  schema,
  authorizationModes: {
    defaultAuthorizationMode: 'userPool',
  },
});
