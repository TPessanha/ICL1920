//10:int
println(let x:ref int= new 5 in let y : ref ref int = new x in let z: ref ref ref int =new y in let g: ref ref ref ref int= new z in let h: ref ref ref ref ref int = new g in !!!!!h + let r: ref int = new 4 in !r+1 end end end end end end);;
