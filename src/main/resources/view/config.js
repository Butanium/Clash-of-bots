import { GraphicEntityModule } from './entity-module/GraphicEntityModule.js';
import { ViewportModule } from './viewport-module/ViewportModule.js';
import { TooltipModule } from './tooltip-module/TooltipModule.js';
import { EndScreenModule } from './endscreen-module/EndScreenModule.js';
// List of viewer modules that you want to use in your game
export const modules = [
  GraphicEntityModule,
  TooltipModule,
  ViewportModule,
  EndScreenModule

];
export const playerColors = [
  '#ff0000', // radical red
  '#0f6a00', // curious blue
  '#ff8f16', // west side orange
  '#6ac371', // mantis green
  '#9975e2', // medium purple
  '#3ac5ca', // scooter blue
  '#de6ddf', // lavender pink
  '#ff0000'  // solid red
];

